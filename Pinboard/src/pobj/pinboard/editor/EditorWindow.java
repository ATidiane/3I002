package pobj.pinboard.editor;

import java.io.File;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandDelete;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandStack;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class EditorWindow implements EditorInterface,ClipboardListener {
	
	private Tool tool = new ToolRect();
	private Board board;
	private Canvas canvas;
	private Selection selected;
	private Clipboard c;
	private Color color = Color.BLACK;
	private MenuItem iPaste,iUndo,iRedo;
	private CommandStack stack;
	
	public EditorWindow(final Stage stage) {
		
		board = new Board();
		selected = new Selection();
		c = Clipboard.getInstance();
		stack = new CommandStack();
		
		stage.setTitle("PinBoard");
		
		final Menu mFile = new Menu("File");
		final Menu mEdit= new Menu("Edit");
		final Menu mTools = new Menu("Tools");
		
		MenuItem iNew = new MenuItem("New");
		MenuItem iClose = new MenuItem("Close");
		mFile.getItems().addAll(iNew, iClose);
		
		MenuItem iEllipse = new MenuItem("Ellipse");
		MenuItem iBox = new MenuItem("Box");
		MenuItem iImg = new MenuItem("Img...");
		MenuItem iSelect = new MenuItem("Select");
		mTools.getItems().addAll(iBox, iEllipse, iImg, iSelect);
		
		MenuItem iCopy = new MenuItem("Copy");
		iPaste = new MenuItem("Paste");
		MenuItem iDelete = new MenuItem("Delete");
		MenuItem iGroup = new MenuItem("Group");
		MenuItem iUngroup = new MenuItem("Ungroup");
		iUndo = new MenuItem("Undo");
		iRedo = new MenuItem("Redo");
		mEdit.getItems().addAll(iCopy,iPaste,iDelete,iGroup,iUngroup,iUndo,iRedo);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(mFile, mEdit, mTools);
		
		Button box = new Button("Box");
		Button ellipse = new Button("Ellipse");
		Button img = new Button("Img...");
		Button select = new Button("Select");
		
		ToolBar toolBar = new ToolBar(box, ellipse, img, select);
		final Rectangle c1 = new Rectangle(50,30);
		final Rectangle c2 = new Rectangle(50,30);
		final Rectangle c3 = new Rectangle(50,30);
		
		c1.setFill(Color.PINK);
		c2.setFill(Color.GREEN);
		c3.setFill(Color.DARKORCHID);
		ToolBar colorBar = new ToolBar(c1, c2, c3);
		
		canvas = new Canvas(400, 400);
		
		Separator s = new Separator();
		
		final Label l = new Label("Filled Rectangle tool");
		
		// La fenêtre s'enregistre auprès du clipboard
		c.addListener(this);
		// Pour pouvoir le désenregistre lors de sa fermeture
		final EditorWindow ew = this;
		
		
	
		
		iNew.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	 new EditorWindow(new Stage());	
		    	 c.notifierListeners();
		    }
		});
		
		iClose.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	stage.close();
		    	c.removeListener(ew);
		    	
		    }
		});
		
		final EventHandler<ActionEvent> boxHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tool = new ToolRect();
				l.setText("Filled "+ tool.getName() +" tool");
				
			}
		};
		
		final EventHandler<ActionEvent> EllipseHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tool = new ToolEllipse();
				l.setText("Filled "+ tool.getName() + " tool");
				
			}
		};
		
		final EventHandler<ActionEvent> imgHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser file = new FileChooser();
				file.setTitle("Choose File");
				file.getExtensionFilters().addAll(
						new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				l.setText("Filled "+ tool.getName() + " tool");
				
				File selectedFile = file.showOpenDialog(stage);
				if (selectedFile != null) {
					tool = new ToolImage(selectedFile);
				}
				
			}
		};
		
		final EventHandler<ActionEvent> SelectHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tool = new ToolSelection();
				l.setText("Filled "+ tool.getName() + " tool");
				
			}
		};
		
		final EventHandler<ActionEvent> CopyHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!selected.getContents().isEmpty()) {
					c.copyToClipBoard(selected.getContents());
				}
				
				//c.notifierListeners();
				l.setText("Copy");
				
			}
		};
		
		final EventHandler<ActionEvent> PasteHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (Clip cl: c.copyFromClipboard()) {
					//board.addClip(cl);
					CommandAdd ca = new CommandAdd(ew, cl);
					ca.execute();
					//stack.addCommand(ca);
					cl.draw(canvas.getGraphicsContext2D());
				}
				c.notifierListeners();
				l.setText("Paste");
				
			}
		};
		
		final EventHandler<ActionEvent> DeleteHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//c.copyFromClipboard().removeAll(selected.getContents());
				//c.clear();
				CommandDelete cd = new CommandDelete(board,canvas,selected);
				cd.execute();
				stack.addCommand(cd);
				System.out.println("After Delete");
				c.notifierListeners();
				l.setText("Delete");
				
			}
		};
		
		final EventHandler<ActionEvent> GroupHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CommandGroup cg = new CommandGroup(board, canvas, selected);
				cg.execute();
				stack.addCommand(cg);
				c.notifierListeners();
				l.setText("Group");
				
			}
		};
		
		final EventHandler<ActionEvent> UnGroupHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CommandUngroup cug = new CommandUngroup(board,canvas,selected);
				cug.execute();
				stack.addCommand(cug);
				c.notifierListeners();
				l.setText("Group");
				
			}
		};
		
		final EventHandler<ActionEvent> UndoHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stack.undo();
				System.out.println("After undo");
				c.notifierListeners();
				l.setText("Undo");
			}
		};
		

		final EventHandler<ActionEvent> RedoHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stack.redo();
				c.notifierListeners();
				l.setText("Redo");
			}
		};
		
		c1.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				color = (Color) c1.getFill();
				
			}
		});
		
		c2.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				color = (Color) c2.getFill();
				
			}
		});
		
		c3.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				color = (Color) c3.getFill();
				
			}
		});
		
		box.setOnAction(boxHandler);
		iBox.setOnAction(boxHandler);
		
		ellipse.setOnAction(EllipseHandler);
		iEllipse.setOnAction(EllipseHandler);
		
		img.setOnAction(imgHandler);
		iImg.setOnAction(imgHandler);
		
		select.setOnAction(SelectHandler);
		iSelect.setOnAction(SelectHandler);
		
		iCopy.setOnAction(CopyHandler);
		iPaste.setOnAction(PasteHandler);
		iDelete.setOnAction(DeleteHandler);
		
		iGroup.setOnAction(GroupHandler);
		iUngroup.setOnAction(UnGroupHandler);
		
		iUndo.setOnAction(UndoHandler);	
		iRedo.setOnAction(RedoHandler);
		
		press(this);		
		drag(this);
		release(this);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(menuBar, toolBar, colorBar, canvas, s, l);
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		
		board.draw(canvas.getGraphicsContext2D());
		stage.show();
		
		
	}
	
	public void press(final EditorInterface ei) {
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.press(ei, e);
				
			}
			
		});
	}
	
	public void drag(final EditorInterface ei) {
		canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.drag(ei, e);
				draw();
			}
			
		});
	}

	public void release(final EditorInterface ei) {
		canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				tool.release(ei, e);
				board.draw(canvas.getGraphicsContext2D());
				if (tool instanceof ToolSelection) {
					tool.drawFeedback(ei, canvas.getGraphicsContext2D());
				}
			}
			
		});
	}
 	
	
	
	public void draw() {
		board.draw(canvas.getGraphicsContext2D());
		tool.drawFeedback(this, canvas.getGraphicsContext2D());
	}
	
	@Override
	public Board getBoard() {
		return board;
	}
	
	public Selection getSelection() {
		return selected;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public void clipboardChanged() {
		if(c.isEmpty() ) {
			iPaste.setDisable(true);
		} else {
			iPaste.setDisable(false);
		}
		if (stack.isRedoEmpty()) {
			System.out.println("Redo Empty");
			iRedo.setDisable(true);
		}else {
			System.out.println("Redo Not empty");
			iRedo.setDisable(false);
		}
		if (stack.isUndoEmpty()) {
			System.out.println("Undo Empty");
			iUndo.setDisable(true);
		}else {
			System.out.println("Undo not Empty");
			iUndo.setDisable(false);
		}
		
	}
	
	
	//Brouillon
	/*class ResizableCanvas extends Canvas {

    public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        board.draw(this.getGraphicsContext2D());
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
    
    @Override
    public double minHeight(double width)
    {
        return 400;
    }

    @Override
    public double maxHeight(double width)
    {
        return 1000;
    }

	}*/
	//canvas = new ResizableCanvas();
	/*InvalidationListener listener = new InvalidationListener(){
    @Override
    public void invalidated(Observable o) {
        redraw();       
    }

	private void redraw() {
		canvas.getGraphicsContext2D().setFill(Color.WHITE);
	    canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}           
	};

	canvas.widthProperty().addListener(listener);
	canvas.heightProperty().addListener(listener);
	 */
}
