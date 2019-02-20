package sample;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label speedLabel;
    @FXML
    private Label engineLabel;
    @FXML
    private Label gearLabel;
    @FXML
    private Circle power;
    @FXML
    private Label pow;
    @FXML
    private Label km;
    @FXML
    private Label rp;

    private boolean isOn = false;
    private boolean canSpeed = true;
    private int currentSpeed = 0;
    private int maxGearSpeed;
    private int minSpeed = 0;
    private int obroty = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pane.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                if (!isOn) {
                    isOn = true;
                    canSpeed=false;
                    power.setFill(Paint.valueOf("#2CFF32"));
                    pow.setText("Engine On");
                    pow.setTextFill(Paint.valueOf("yellow"));
                    speedLabel.setVisible(true);
                    gearLabel.setVisible(true);
                    engineLabel.setVisible(true);
                    km.setVisible(true);
                    rp.setVisible(true);
                } else {
                    isOn = false;
                    power.setFill(Paint.valueOf("#FF0C0C"));
                    pow.setText("Engine Off");
                    pow.setTextFill(Paint.valueOf("black"));
                    speedLabel.setVisible(false);
                    gearLabel.setVisible(false);
                    engineLabel.setVisible(false);
                    km.setVisible(false);
                    rp.setVisible(false);
                }
            }
        });

        pane.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (isOn) {
                    switch (event.getCharacter()) {
                        case "0":
                            gearLabel.setText("N");
                            canSpeed = false;
                            break;
                        case "1":
                            gearLabel.setText("1");
                            canSpeed = true;
                            maxGearSpeed = 50;
                            break;
                        case "2":
                            gearLabel.setText("2");
                            canSpeed = true;
                            maxGearSpeed = 100;
                            break;
                        case "3":
                            gearLabel.setText("3");
                            canSpeed = true;
                            maxGearSpeed = 150;
                            break;
                        case "4":
                            gearLabel.setText("4");
                            canSpeed = true;
                            maxGearSpeed = 200;
                            break;
                        case "5":
                            gearLabel.setText("5");
                            canSpeed = true;
                            maxGearSpeed = 250;
                            break;
                        case "6":
                            gearLabel.setText("6");
                            canSpeed = true;
                            maxGearSpeed = 300;
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        pane.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (isOn && canSpeed) {
                if (keyEvent.getCode() == KeyCode.W) {
                    if (currentSpeed <= maxGearSpeed) {
                        currentSpeed++;
                        obroty = (currentSpeed * 7000 / maxGearSpeed);
                        speedLabel.setText(Integer.toString(currentSpeed));
                        engineLabel.setText(Integer.toString(obroty));
                    }
                } else if (keyEvent.getCode() == KeyCode.S) {
                    if (currentSpeed > minSpeed) {
                        --currentSpeed;
                        obroty = (currentSpeed * 7000 / maxGearSpeed);
                        speedLabel.setText(Integer.toString(currentSpeed));
                        engineLabel.setText(Integer.toString(obroty));
                    }
                }
            }
        });

        update();


    }


    private void update() {

        PauseTransition pause = new PauseTransition(Duration.millis(200));
        pause.setOnFinished(event ->{
            if(currentSpeed > 0) {
                currentSpeed--;
                obroty = (currentSpeed * 7000 / maxGearSpeed);
                speedLabel.setText(Integer.toString(currentSpeed));
                engineLabel.setText(Integer.toString(obroty));
            }
            pause.play();
        });
        pause.play();
    }

}
