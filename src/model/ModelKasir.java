/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ControllerKasir;
import java.sql.SQLException;
import view.Kasir;
import database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author lotso
 */
public class ModelKasir implements ControllerKasir {

    @Override
    public void Simpan(Kasir kasir) throws SQLException {
        String cb = "Tidak memilih topping";

        if (kasir.cbkeju.isSelected()) {
            cb = "Mozarella Cheese";
        }
        if (kasir.cbsosis.isSelected()) {
            cb = "Sausage";
        }
        if (kasir.cbdaging.isSelected()) {
            cb = "Smoked Beef";
        }
        if (kasir.cbkeju.isSelected() && kasir.cbsosis.isSelected()) {
            cb = "Mozarella Cheese & Sausage";
        }
        if (kasir.cbkeju.isSelected() && kasir.cbdaging.isSelected()) {
            cb = "Mozarella Cheese & Smoked Beef";
        }
        if (kasir.cbsosis.isSelected() && kasir.cbdaging.isSelected()) {
            cb = "Sausage & Smoked Beef";
        }
        if (kasir.cbkeju.isSelected() && kasir.cbsosis.isSelected() && kasir.cbdaging.isSelected()) {
            cb = "Mozarella Cheese, Sausage, Smoked Beef";
        }

        try {
            Connection con = Koneksi.getCon();
            String sql = "insert into kasir values (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, (String) kasir.cmbnm.getSelectedItem());
            ps.setString(2, kasir.txtharga.getText());
            ps.setString(3, cb);
            ps.setString(4, kasir.txtjml.getText());
            ps.setString(5, kasir.txtbayar.getText());
            ps.setString(6, kasir.txtkembalian.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data sukses disimpan");
            ps.close();
            Batal(kasir);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
            System.err.println("Error " + e);

        } finally {
            Tampil(kasir);
            kasir.setLebarKolom();
        }
    }

    @Override
    public void Batal(Kasir kasir) throws SQLException {
        JCheckBox[] arrayTopping = new JCheckBox[]{kasir.cbkeju, kasir.cbsosis, kasir.cbdaging};
        for (int i = 0; i < arrayTopping.length; i++) {
            arrayTopping[i].setSelected(false);
            kasir.cmbnm.setSelectedIndex(0);
            kasir.txtharga.setText("");
            kasir.txtjml.setText("");
            kasir.txtbayar.setText("");
            kasir.txtkembalian.setText("");
        }
    }

    @Override
    public void Tampil(Kasir kasir) throws SQLException {
        kasir.tblmodel.getDataVector().removeAllElements();
        kasir.tblmodel.fireTableDataChanged();
        try {
            Connection con = Koneksi.getCon();
            Statement stt = con.createStatement();
       
            String sql = "select * from kasir order by nama_pizza asc";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                kasir.tblmodel.addRow(ob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void KlikTable(Kasir kasir) throws SQLException {
        try {
            int pilih = kasir.tbl.getSelectedRow();
            if (pilih == -1) {
                return;
            }
            kasir.cmbnm.setSelectedItem(kasir.tblmodel.getValueAt(pilih, 0).toString());
            kasir.txtharga.setText(kasir.tblmodel.getValueAt(pilih, 1).toString());
            kasir.txtjml.setText(kasir.tblmodel.getValueAt(pilih, 3).toString());
            kasir.txtbayar.setText(kasir.tblmodel.getValueAt(pilih, 4).toString());
            kasir.txtkembalian.setText(kasir.tblmodel.getValueAt(pilih, 5).toString());
            cb = String.valueOf(kasir.tblmodel.getValueAt(pilih, 2));

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void TampilNamaPizza(Kasir kasir) throws SQLException {
        try {
            Connection con = Koneksi.getCon();
            String sql = "select * from data";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kasir.cmbnm.addItem(rs.getString("nama_pizza"));
            }
            rs.last();
            int jumlahdata = rs.getRow();
            rs.first();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void TampilHarga(Kasir kasir) throws SQLException {
        String tmp = (String) kasir.cmbnm.getSelectedItem();
        try {
            Connection con = Koneksi.getCon();
            String sql = "SELECT harga_pizza FROM data WHERE nama_pizza=?";
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, tmp);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                kasir.txtharga.setText(rs.getString("harga_pizza"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
