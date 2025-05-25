/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.models.interfaces.Observer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdiaz
 */
public class TableRefresherObserver implements Observer {

    private final Runnable onUpdate;

    public TableRefresherObserver(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    @Override
    public void update() {
        onUpdate.run();
    }
}

