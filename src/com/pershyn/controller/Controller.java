package com.pershyn.controller;

//import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.pershyn.entity.Item;
import com.pershyn.service.ItemService;
import com.pershyn.service.Registrator;
import com.pershyn.service.XMLReader;
import com.pershyn.service.XMLWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    private Button searchingButton = new Button();
    @FXML
    private TextField linkTextField = new TextField();
    @FXML
    private TextArea resultField = new TextArea();

    @FXML
    private TextField emailField = new TextField();
    @FXML
    private TextField pasField = new TextField();
    @FXML
    private TextField nameField = new TextField();
    @FXML
    private Button registerButton = new Button();

    @FXML
    private TextField loginField = new TextField();
    @FXML
    private TextField passwordField = new TextField();
    @FXML
    private TextField itemId = new TextField();
    @FXML
    private Button signButton = new Button();
    @FXML
    private Button addToCartButton = new Button();


    public void onButtonSearchClick() {

        List<Item> itemList = new ArrayList<Item>();
        itemList = XMLReader.initReading();
        Item item = ItemService.getItemByUrl(linkTextField.getText());
        itemList.add(item);
        XMLWriter.initWritingXML(itemList);
        itemList = XMLReader.initReading();
        StringBuilder stringBuilder = new StringBuilder();
        for (Item itemEl : itemList) {
            stringBuilder.append(itemEl.toString());
        }
//
        resultField.setText(stringBuilder.toString());
    }

    public void createAccount() {
        Registrator.registerAccount(emailField.getText(), pasField.getText(), nameField.getText());
    }

    public void signIn() {
        Registrator.signIn(loginField.getText(), passwordField.getText());
    }


    public void addToCartButtonClick() {
        Item item = Registrator.addToCart(itemId.getText());
        System.out.println(item.toString());
    }
}