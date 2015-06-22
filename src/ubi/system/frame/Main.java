package ubi.system.frame;

import java.util.ServiceLoader;

import org.opencv.core.Core;

import ubi.system.plugin.PluginFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		StackPane	root	= new StackPane();
		
		TabPane		tabs	= new TabPane();
		root.getChildren().add(tabs);
			
		loadPlugins(tabs);
			
		Scene scene = new Scene(root , 800 , 450);
		primaryStage.setTitle("備品管理システム");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop(){
		
	}
	
	private void loadPlugins(TabPane tabs){
		ServiceLoader<PluginFactory>	loader = ServiceLoader.load(PluginFactory.class);
		loader.forEach(factory -> {
			Tab tab = new Tab(factory.getName());
			factory.createPlugin().ifPresent(plugin -> tab.setContent(plugin.getContent()));
			tabs.getTabs().add(tab);
			factory.startPlugin();
		});
	}
}
