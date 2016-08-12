package com.bonnenuit.controler;

import com.bonnenuit.model.DatabaseManager;
import com.bonnenuit.view.View;

/**
 * Created by Rostyslav on 09/08/16.
 */
public class Controller {
    private View view;
    private DatabaseManager manager;
    private boolean forever;
    public Controller(View view, DatabaseManager manager,boolean forever){
        this.view = view;
        this.manager = manager;
        this.forever = forever;
    }

    public void controllerRunner(){
        System.out.println("Input parameters to connect like these url:username:password. \nFor example localhost:5432:root:pass");

        if(!manager.connected()) {
            connectToDb();
        }

        System.out.println("Input command or \"help\" to show info.");
        do{

            String command = view.read();
            if(command.equals("help")){
            help();
            }else if(command.equals("tablelist")){
            manager.showDb();
            }else if(command.startsWith("input:")){
            insertToTable();
            }else if(command.startsWith("select:")){
            showTable(command);
            }else if(command.startsWith("clearrow:")){
            clear();
            }else if (command.equals("edit")){
              edit();
            }else if (command.equals("exit")){
                System.exit(1);
            }else{
                System.out.println("Such command does not exist!\n" +
                        "Try \"help\" to com.bonnenuit.view info...");

            }
        }while (forever);
    }

    private void edit() {
        String parameter=view.read();
        String[] parameters=parameter.split(":");
        if(parameters.length!=3){
            throw new IllegalArgumentException("not enough parameters to connect, current value" +parameters.length);
        }
        String tableName=parameters[1];
        String name=parameters[2];
        String password=parameters[3];
        manager.update(tableName,name,password);
    }

    private void clear() {
        String parameter=view.read();
        String[] parameters=parameter.split(":");
        if(parameters.length!=2){
            throw new IllegalArgumentException("not enough parameters to connect, current value" +parameters.length);
        }
        String id=parameters[1];
        manager.delete(id);
    }

    private void showTable(String command) {
        String[] parameters=command.split(":");
        if(parameters.length!=2){
            throw new IllegalArgumentException("not enough parameters to connect, current value " +parameters.length);
        }
        String tableName=parameters[1];
                manager.select(tableName);
    }

    private void insertToTable() {
            String parameter=view.read();
            String[] parameters=parameter.split(":");
            if(parameters.length!=4){
                throw new IllegalArgumentException("not enough parameters to connect, current value" +parameters.length);
            }
            String tableName=parameters[1];
            String user=parameters[2];
            String password=parameters[3];
            manager.insert(tableName,user,password);
    }

    public void connectToDb(){
        String parameter=view.read();
            String[] parameters=parameter.split(":");
            if(parameters.length!=4) {
                throw new IllegalArgumentException("not enough parameters to connect, current value" + parameters.length);
            }
            String url=parameters[0]+":"+parameters[1];
            String user=parameters[2];
            String password=parameters[3];
            manager.connect(url,user,password);
    }

    public void help(){
    System.out.println("\"tablelist\" - show tables list \n " +
            "\"input:(tablename:user:password)\" - insert data to table \n " +
            "\"showtable:(table name)\"- show data from table \n " +
            "\"clearrow:(id of row)\" -delete data from row \n " +
            "\"edit\"-change change password by name in table \n"+
            "\"exit\" - to finish use program"
    );
}
}
