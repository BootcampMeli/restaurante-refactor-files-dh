package br.com.mercadolivre.restauranterefactorfilesdh.repositories;

import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PedidoFinalizadoDTO;
import br.com.mercadolivre.restauranterefactorfilesdh.dtos.PratoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositorie {

    public List<PedidoFinalizadoDTO> retornaPedidosPorMesa(Integer id) {
        List<PedidoFinalizadoDTO> pedidoFinalizadoDTOS = null;
        try {
            pedidoFinalizadoDTOS = lerPedidosPorMesaDoArquivo(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pedidoFinalizadoDTOS;
    }

    public PedidoFinalizadoDTO retornaPedido(Integer id) {
        try {
            return lerPedidoDoArquivo(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void inserePedido(PedidoDTO pedido, Integer id) {
        try {
            inserePedidoNaMesa(pedido, id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private List<PedidoFinalizadoDTO> lerPedidosPorMesaDoArquivo(Integer id) throws JsonProcessingException {
        List<PedidoFinalizadoDTO> pedidosFinalizados = new ArrayList<>();

        JSONArray mesas = retornaArrayDeMesas();
        JSONObject mesa = null;

        for(int i = 0; i < mesas.size(); i++){
            JSONObject mesaAux = (JSONObject) mesas.get(i);

            if(mesaAux.get("id").toString().equals(id.toString())){
                mesa = mesaAux;
            }
        }

        JSONArray pedidos = (JSONArray) mesa.get("pedidos");

        ObjectMapper objectMapper = new ObjectMapper();
        for(int i = 0; i < pedidos.size(); i++){
            JSONObject pedido = (JSONObject) pedidos.get(i);

            pedido.remove("fechado");
            pedido.put("valorTotal", 0.0);

            PedidoFinalizadoDTO pedidoDTO = objectMapper.readValue(pedido.toString(), PedidoFinalizadoDTO.class);
            pedidoDTO.setValorTotal(pedidoDTO.getPratos().stream().mapToDouble(prato -> prato.getPreco() * prato.getQuantidade()).sum());
            pedidosFinalizados.add(pedidoDTO);
        }

        return pedidosFinalizados;
    }

    private PedidoFinalizadoDTO lerPedidoDoArquivo(Integer id) throws JsonProcessingException {
        PedidoFinalizadoDTO pedidoFinalizado = null;
        JSONArray mesas = retornaArrayDeMesas();

        ObjectMapper objectMapper = new ObjectMapper();
        for(int i = 0; i < mesas.size(); i++){
            JSONObject mesaAux = (JSONObject) mesas.get(i);
            JSONArray pedidos = (JSONArray) mesaAux.get("pedidos");

            for(int j = 0; j < pedidos.size(); j++){
                JSONObject pedido = (JSONObject) pedidos.get(j);

                pedido.remove("fechado");
                pedido.put("valorTotal", 0.0);

                if(pedido.get("id").toString().equals(id.toString())){
                    pedidoFinalizado = objectMapper.readValue(pedido.toString(), PedidoFinalizadoDTO.class);
                }
            }
        }

        pedidoFinalizado.setValorTotal(pedidoFinalizado.getPratos().stream().mapToDouble(prato -> prato.getPreco() * prato.getQuantidade()).sum());


        return pedidoFinalizado;
    }

    private void inserePedidoNaMesa(PedidoDTO pedido, Integer id) throws JsonProcessingException, ParseException {
        JSONArray mesas = retornaArrayDeMesas();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();

        JSONObject mesa = null;
        for(int i = 0; i < mesas.size(); i++){
            JSONObject mesaAux = (JSONObject) mesas.get(i);

            if(mesaAux.get("id").toString().equals(id.toString())){
                mesa = mesaAux;
            }
        }

        JSONArray pedidos = (JSONArray) mesa.get("pedidos");


        pedidos.add(pedidoParaJson(pedido));

        JSONObject objFinal = new JSONObject();
        objFinal.put("mesas", mesas);

        escreverNoJSON(objFinal);
    }

    private JSONObject pedidoParaJson(PedidoDTO pedidoDTO){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", pedidoDTO.getId());
        jsonObject.put("fechado", pedidoDTO.isFechado());

        JSONArray pratoArr = new JSONArray();
        for(PratoDTO prato : pedidoDTO.getPratos()){
            JSONObject pratoObj = new JSONObject();
            pratoObj.put("id", prato.getId());
            pratoObj.put("preco", prato.getPreco());
            pratoObj.put("descricao", prato.getDescricao());
            pratoObj.put("quantidade", prato.getQuantidade());

            pratoArr.add(pratoObj);
        }

        jsonObject.put("pratos", pratoArr);

        return jsonObject;
    }

    private JSONArray retornaArrayDeMesas(){
        JSONParser jsonParser = new JSONParser();
        JSONArray mesas = null;
        try(FileReader fileReader = new FileReader("banco.json")) {
            Object obj = (Object) jsonParser.parse(fileReader);

            JSONObject objJson = (JSONObject) obj;
            mesas = (JSONArray) objJson.get("mesas");

        }catch (IOException e){

        }catch (ParseException e) {
            e.printStackTrace();
        }
        return mesas;
    }

    private void escreverNoJSON(JSONObject jsonObject){
        try (FileWriter file = new FileWriter("banco.json")) {
            file.write(jsonObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
