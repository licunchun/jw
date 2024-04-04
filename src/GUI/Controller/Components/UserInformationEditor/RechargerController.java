package GUI.Controller.Components.UserInformationEditor;

import GUI.Data.Enum.Error.Main.Components.UserServ.EditError;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Sevice.Main.Components.UserServ.UserServ.editAddMoney;

public class RechargerController {
    @FXML
    private Label tips;
    @FXML
    private Button MoneyAdd10;
    @FXML
    private Button MoneyAdd20;
    @FXML
    private Button MoneyAdd50;
    @FXML
    private Button MoneyAdd100;
    @FXML
    private Button MoneyAdd200;
    @FXML
    private Button MoneyAdd500;
    private String ID;
    private Stage stage;
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle("充值界面");
    }
    @FXML
    private void initialize(){}
    @FXML
    private void doMoneyAdd10(){
        switch(editAddMoney(ID,10)){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
    @FXML
    private void doMoneyAdd20(){
        switch(editAddMoney(ID,20)){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
    @FXML
    private void doMoneyAdd50(){
        switch(editAddMoney(ID,50)){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
    @FXML
    private void doMoneyAdd100(){
        switch(editAddMoney(ID,100)){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
    @FXML
    private void doMoneyAdd200(){
        switch(editAddMoney(ID,200)){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
    @FXML
    private void doMoneyAdd500(){
        switch(editAddMoney(ID,500)){
            case EditError.IDNotFound:
                tips.setText("未找到ID，请重新登录！！");
                tips.setVisible(true);
                break;
            case Success:
                stage.close();
        }
    }
}
