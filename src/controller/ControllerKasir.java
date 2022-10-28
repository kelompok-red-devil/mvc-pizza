/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import java.sql.SQLException;
import view.Kasir;

/**
 *
 * @author lotso
 */
public interface ControllerKasir {
    public void Simpan(Kasir kasir)throws SQLException;
    public void Batal(Kasir kasir)throws SQLException;
    public void Tampil(Kasir kasir)throws SQLException;
    public void KlikTable(Kasir kasir)throws SQLException;
}
