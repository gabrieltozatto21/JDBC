/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Control.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
// Classe responsável por gerar um objeto que vai acessar o banco de dados.
public class ClientesDAO {

    private Connection conectar;

    // Método construtor.
    public ClientesDAO() {
        this.conectar = new ConnectionFactory().conectar();
    }

    // ***Metodos***
    //--------------------------------------------------------------------------
    public void CadastrarClientes(Cliente obj) {
        try {
            //1º passo: criar comando sql.
            String cmdsql = "insert into cliente (cpf_cliente, nome_cliente, debito,cell_cliente) values(?,?,?,?)";

            //2º passo: organizar o comando sql(cmdsql)e executá-lo.
            PreparedStatement stmt = conectar.prepareStatement(cmdsql);
            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setDouble(3, obj.getDebito());
            stmt.setString(4, obj.getTelefone());

            //3º passo: executar o comando.
            stmt.execute();

            //4º passo: fechar a conexão.
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    //--------------------------------------------------------------------------
    public List<Cliente> ListarClientes() {
        try {
            //1º passo: criar o vetor que vai armazenar os registros do banco.
            List<Cliente> lista = new ArrayList<Cliente>();

            //2º passo: criar o comando sql
            String cmdSql = "select * from cliente";

            PreparedStatement stmt = conectar.prepareStatement(cmdSql);

            //3º passo: guardar o resultado do select dentro do objeto RS (resultSet).
            ResultSet rs = stmt.executeQuery();

            //4º passo: Enquanto houver registros (resultado do select) guarde o registro na lista.
            while (rs.next()) {

                Cliente v = new Cliente();
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setCpf(rs.getString("cpf_cliente"));
                v.setNome(rs.getString("nome_cliente"));
                v.setDebito(rs.getDouble("debito"));
                v.setTelefone(rs.getString("cell_cliente"));

                lista.add(v);

            }

            stmt.close();
            rs.close();

            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //--------------------------------------------------------------------------
    public List<Cliente> ListarClientesPorCpf(String Cpf) {
        try {
            //1º passo: criar o vetor que vai armazenar os registros do banco.
            List<Cliente> lista = new ArrayList<Cliente>();

            //2º passo: criar o comando sql
            String cmdSql = "select * from cliente where cpf_cliente like ?";

            PreparedStatement stmt = conectar.prepareStatement(cmdSql);
            stmt.setString(1, Cpf);
            //3º passo: guardar o resultado do select dentro do objeto RS (resultSet).
            ResultSet rs = stmt.executeQuery();

            //4º passo: Enquanto houver registros (resultado do select) guarde o registro na lista.
            while (rs.next()) {

                Cliente v = new Cliente();
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setCpf(rs.getString("cpf_cliente"));
                v.setNome(rs.getString("nome_cliente"));
                v.setDebito(rs.getDouble("debito"));
                v.setTelefone(rs.getString("cell_cliente"));

                lista.add(v);

            }

            stmt.close();
            rs.close();

            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    //--------------------------------------------------------------------------
    public void EditarClientes(Cliente obj) {
        try {
            //1º passo: criar comando sql.
            String cmdsql = "update cliente set cpf_cliente=? , nome_cliente=?, debito=?, cell_cliente=? where cpf_cliente=?";

            //2º passo: organizar o comando sql(cmdsql)e executá-lo.
            PreparedStatement stmt = conectar.prepareStatement(cmdsql);
            stmt.setString(1, obj.getCpf());
            stmt.setString(2, obj.getNome());
            stmt.setDouble(3, obj.getDebito());

            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCpf());

            //3º passo: executar o comando.
            stmt.execute();

            //4º passo: fechar a conexão.
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    //---------------------------------------------------------------------------
    public void ExcluirClientes(Cliente obj) {
        try {
            //1º passo: criar comando sql.
            String cmdsql = "delete from cliente where id_cliente=?";

            //2º passo: organizar o comando sql(cmdsql)e executá-lo.
            PreparedStatement stmt = conectar.prepareStatement(cmdsql);
            stmt.setInt(1, obj.getIdCliente());

            //3º passo: executar o comando.
            stmt.execute();

            //4º passo: fechar a conexão.
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    //--------------------------------------------------------------------
    public void Fechar() {
        try {
            this.conectar.close();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
    }

}
