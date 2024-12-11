<?xml version="1.0" encoding="UTF-8"?>

        <?import javafx.scene.control.*?>
        <?import javafx.scene.layout.*?>
        <?import javafx.scene.image.*?>

<BorderPane xmlns:fx="http://javafx.com/fxml" stylesheets="@style.css" fx:controller="com.example.co2mpare.SettingsController">
    <top>
        <HBox spacing="10" style="-fx-background-color: #d3d3d3; -fx-padding: 10;">
            <ImageView fitHeight="20" fitWidth="20" pickOnBounds="true" preserveRatio="true">
                <image>
                    <Image url="@logo.png"/>
                </image>
            </ImageView>
            <Label text="CO₂MPARE" style="-fx-font-size: 18px; -fx-font-weight: bold;"/>
        </HBox>
    </top>
    <center>
        <VBox spacing="10" style="-fx-padding: 20;">
            <TitledPane text="Account">
                <VBox spacing="10" style="-fx-padding: 10;">
                    <Label text="Account"/>
                    <Label text="My Subscription"/>
                </VBox>
            </TitledPane>
            <TitledPane text="Settings">
                <VBox spacing="10" style="-fx-padding: 10;">
                    <Label text="General Settings"/>
                    <Label text="Privacy Settings"/>
                    <Label text="Notification Preferences"/>
                    <Label text="Appearance Settings"/>
                    <Label text="Connect with Micro:bit"/>
                    <Label text="Advanced Settings"/>
                </VBox>
            </TitledPane>
            <TitledPane text="Support">
                <VBox spacing="10" style="-fx-padding: 10;">
                    <Label text="Help and Support"/>
                    <Label text="Sign Out"/>
                </VBox>
            </TitledPane>
        </VBox>
    </center>
    <bottom>
        <HBox spacing="20" style="-fx-background-color: #d3d3d3; -fx-padding: 10; -fx-alignment: center;">
            <ToggleGroup fx:id="navigationGroup"/>

            <ToggleButton text="Home" style="-fx-font-size: 14px;" toggleGroup="$navigationGroup">
                <graphic>
                    <ImageView fitWidth="20" fitHeight="20">
                        <image>
                            <Image url="@home_icon.png"/>
                        </image>
                    </ImageView>
                </graphic>
            </ToggleButton>

            <ToggleButton text="Usage" style="-fx-font-size: 14px;" toggleGroup="$navigationGroup">
                <graphic>
                    <ImageView fitWidth="20" fitHeight="20">
                        <image>
                            <Image url="@usage_icon.png"/>
                        </image>
                    </ImageView>
                </graphic>
            </ToggleButton>

            <ToggleButton text="Settings" style="-fx-font-size: 14px;" toggleGroup="$navigationGroup" selected="true">
                <graphic>
                    <ImageView fitWidth="20" fitHeight="20">
                        <image>
                            <Image url="@settings_icon.png"/>
                        </image>
                    </ImageView>
                </graphic>
            </ToggleButton>
        </HBox>
    </bottom>
</BorderPane>
