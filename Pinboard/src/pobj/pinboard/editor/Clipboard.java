package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard  {

	private List<Clip> copied;
	private static Clipboard clipboard = new Clipboard();
	private List<ClipboardListener> cibles;
	
	private Clipboard() {
		copied = new ArrayList<Clip>();
		cibles = new ArrayList<ClipboardListener>();
	}
	
	public void copyToClipBoard(List<Clip> clips){
		copied.addAll(clips);
		notifierListeners();
	}
	
	public List<Clip> copyFromClipboard() {
		return copied;
	}
	
	public void clear() {
		copied.clear();
		notifierListeners();
	}
	
	public boolean isEmpty() {
		return copied.isEmpty();
	}
	
	public static Clipboard getInstance() {
		return clipboard;
	}
	
	public void addListener(ClipboardListener listener) {
		cibles.add(listener);
		notifierListeners();
	}
	
	public void removeListener(ClipboardListener listener) {
		cibles.remove(listener);
	}
	
	public void notifierListeners() {
		for (ClipboardListener c: cibles) {
			c.clipboardChanged();
		}
	}
	
	public List<ClipboardListener> getCibles() {
		return cibles;
	}

}
