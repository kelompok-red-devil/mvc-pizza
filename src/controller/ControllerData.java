/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import java.sql.SQLException;
import view.Data;
/**
 *
 * @author lotso
 */
public interface ControllerData {
    public void Simpan(Data data) throws SQLException;
    public void Ubah(Data data) throws SQLException;
    public void Hapus(Data data) throws SQLException;
    public void Batal(Data data) throws SQLException;
    public void KlikTable(Data data) throws SQLException;
    public void Tampil(Data data) throws SQLException;
}
