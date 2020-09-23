/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Ali Reza
 */
public class FXMLDocumentController {
    
    @FXML
    private TextField formula;

    @FXML
    private Label result;

    @FXML
    private JFXButton btn_1;

    @FXML
    private JFXButton btn_2;

    @FXML
    private JFXButton btn_3;

    @FXML
    private JFXButton btn_4;

    @FXML
    private JFXButton btn_5;

    @FXML
    private JFXButton btn_6;

    @FXML
    private JFXButton btn_7;

    @FXML
    private JFXButton btn_8;

    @FXML
    private JFXButton btn_9;

    @FXML
    private JFXButton btn_00;

    @FXML
    private JFXButton btn_0;

    @FXML
    private JFXButton btn_decimal;

    @FXML
    private JFXButton btn_plus;

    @FXML
    private JFXButton btn_minus;

    @FXML
    private JFXButton btn_multiply;

    @FXML
    private JFXButton btn_divide;

    @FXML
    private JFXButton btn_clearAll;

    @FXML
    private JFXButton btn_clear;

    @FXML
    private JFXButton btn_equal;
    
    @FXML
    void btn_1_click(MouseEvent event) {
        formula.appendText("1");
    }
    
    @FXML
    void btn_2_click(MouseEvent event) {
        formula.appendText("2");
    }
    
    @FXML
    void btn_3_click(MouseEvent event) {
        formula.appendText("3");
    }
    
    @FXML
    void btn_4_click(MouseEvent event) {
        formula.appendText("4");
    }
    
    @FXML
    void btn_5_click(MouseEvent event) {
        formula.appendText("5");
    }
    
    @FXML
    void btn_6_click(MouseEvent event) {
        formula.appendText("6");
    }
    
    @FXML
    void btn_7_click(MouseEvent event) {
        formula.appendText("7");
    }
    
    @FXML
    void btn_8_click(MouseEvent event) {
        formula.appendText("8");
    }
    
    @FXML
    void btn_9_click(MouseEvent event) {
        formula.appendText("9");
    }
    
    @FXML
    void btn_0_click(MouseEvent event) {
        formula.appendText("0");
    }
    
    @FXML
    void btn_00_click(MouseEvent event) {
        formula.appendText("00");
    }
    
    @FXML
    void btn_decimal_click(MouseEvent event) {
        formula.appendText(".");
    }
    
    @FXML
    void btn_plus_click(MouseEvent event) {
        formula.appendText("+");
    }
    
    @FXML
    void btn_minus_click(MouseEvent event) {
        formula.appendText("-");
    }
    
    @FXML
    void btn_multiply_click(MouseEvent event) {
        formula.appendText("*");
    }
    
    @FXML
    void btn_divide_click(MouseEvent event) {
        formula.appendText("/");
    }
    
    @FXML
    void btn_clear_click(MouseEvent event) {
        int len = formula.getText().length() - 1;
        
        if(!result.getText().isEmpty() || result.getText().equals("")) {
            result.setText("");
            formula.deleteText(len, len+1);
        }
        else{
            formula.deleteText(len, len+1);
        }
    }
    
    @FXML
    void btn_clearAll_click(MouseEvent event) {
        int len = formula.getText().length() - 1;
        formula.deleteText(0, len+1);
        result.setText("");
    }
    
    @FXML
    void btn_equal_click(MouseEvent event) {
        
        try {
            boolean input_validity = false;
            
            input_validity = check_input(input_validity);
            check_formula(input_validity);
        }
        catch(Exception ex) {
            
        }
    }
    
    
    // ------------------------------------------------------- Input check function ------------------------------------------------------- //
        
    public boolean check_input(boolean x) {
        boolean status = x;
        
        if(formula.getText().isEmpty()) {
            result.setText("No input");
        }
        else if(formula.getText().matches(".*[0123456789./*-+].*")) {
            status = true;
        }
        else{
            status = false;
            result.setText("Unsupported input");
        }
        
        return status;
    }
    
    // ------------------------------------------------------- Formula check function ------------------------------------------------------- //
        
    public void check_formula(boolean x) throws ScriptException {
        boolean isFound = false;
        int len = formula.getText().length() - 1;
        String first = formula.getText().substring(0, 1);
        String last = formula.getText().substring(len, len+1);
        String[] double_sign_list = {"+*", "+/", "-*", "-/", "*/", "/*"};
        
        for(String a: double_sign_list) {
            if(formula.getText().contains(a)) {
                isFound = true;
                break;
            }
        }
        
        if(x == true) {    
            if(first.matches(".*[/*].*")  ||  last.matches(".*[/*-+].*")) {
                result.setText("Invalid format");
            }
            else if(isFound == true) {
                result.setText("Invalid format");
            }
            else{
                calculation();
            }
        }
    }
    
    // ------------------------------------------------------- Calculation function ------------------------------------------------------- //
    
    public void calculation() throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        
        result.setText(engine.eval(formula.getText()).toString());
    }
    
    
}
