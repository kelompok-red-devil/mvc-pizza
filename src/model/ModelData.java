/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ControllerData;
import database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import view.Data;

/**
 *
 * @author lotso
 */
public class ModelData implements ControllerData {

    @Override
    public void Simpan(Data data) throws SQLException {
        try {
            Connection con = Koneksi.getCon();
            String sql = "insert into data values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data.nm_pizza.getText());
            ps.setString(2, data.harga.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            ps.close();
            Batal(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
            System.err.println(e);
        } finally {
            Tampil(data);
            data.setLebarKolom();
        }
    }

    @Override
    public void Ubah(Data data) throws SQLException {
        try {
            Connection con = Koneksi.getCon();
            String sql = "update data set harga_pizza =? where nama_pizza =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(2, data.nm_pizza.getText());
            ps.setString(1, data.harga.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        } finally {
            Tampil(data);
            data.setLebarKolom();
            Batal(data);
        }
    }

    @Override
    public void Hapus(Data data) throws SQLException {
        try {
            Connection con = Koneksi.getCon();
            String sql = "delete from data where nama_pizza=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data.nm_pizza.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
        } finally {
            Tampil(data);
            data.setLebarKolom();
            Batal(data);
        }
    }

    @Override
    public void Batal(Data data) throws SQLException {
        data.nm_pizza.setText("");
        data.harga.setText("");
    }

    @Override
    public void KlikTable(Data data) throws SQLException {
        try {
            int pilih = data.tbl.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            data.nm_pizza.setText(data.tblmodel.getValueAt(pilih, 0).toString());
            data.harga.setText(data.tblmodel.getValueAt(pilih, 1).toString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void Tampil(Data data) throws SQLException {
        data.tblmodel.getDataVector().removeAllElements();
        data.tblmodel.fireTableDataChanged();

        try {
            Connection con = Koneksi.getCon();
            Statement stt = con.createStatement();
            String sql = "select * from data order by nama_pizza asc";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[2];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                data.tblmodel.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
