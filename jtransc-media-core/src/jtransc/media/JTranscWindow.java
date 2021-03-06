package jtransc.media;

import jtransc.annotation.JTranscKeep;

import java.util.ArrayList;

public final class JTranscWindow {
	public interface Handler {
		@JTranscKeep
		void onResized();
	}

	@JTranscKeep
	static private double screenWidth = 640;
	@JTranscKeep
	static private double screenHeight = 480;

	@JTranscKeep
	static private double virtualWidth = 640;
	@JTranscKeep
	static private double virtualHeight = 480;

	@JTranscKeep
	static private double virtualActualWidth = 640;
	@JTranscKeep
	static private double virtualActualHeight = 480;

	@JTranscKeep
	static private double virtualScaleX = 1.0;
	@JTranscKeep
	static private double virtualScaleY = 1.0;

	@JTranscKeep
	static private ArrayList<Handler> handlers = new ArrayList<Handler>();

	@JTranscKeep
	static public void addHandler(Handler handler) {
		handlers.add(handler);
	}

	@JTranscKeep
	static public void dispatchResized() {
		for (Handler handler : handlers) {
			handler.onResized();
		}
	}

	static public void setScreenSize(int screenWidth, int screenHeight) {
		_setSizes(screenWidth, screenHeight, virtualWidth, virtualHeight);
	}

	static public void setVirtualSize(int virtualWidth, int virtualHeight) {
		_setSizes(screenWidth, screenHeight, virtualWidth, virtualHeight);
	}

	static public double getScreenWidth() {
		return screenWidth;
	}

	static public double getScreenHeight() {
		return screenHeight;
	}

	static public double getVirtualWidth() {
		return virtualWidth;
	}

	static public double getVirtualHeight() {
		return virtualHeight;
	}

	static public double getVirtualActualWidth() {
		return virtualActualWidth;
	}

	static public double getVirtualActualHeight() {
		return virtualActualHeight;
	}

	static public double getVirtualScaleX() {
		return virtualScaleX;
	}

	static public double getVirtualScaleY() {
		return virtualScaleY;
	}

	static private boolean _setSizes(double screenWidth, double screenHeight, double virtualWidth, double virtualHeight) {
		//println("${screenWidth}x${screenHeight} :: ${virtualWidth}x${virtualHeight}")
		if ((JTranscWindow.screenWidth != screenWidth) || (JTranscWindow.screenHeight != screenHeight) || (JTranscWindow.virtualWidth != virtualWidth) || (JTranscWindow.virtualHeight != virtualHeight)) {
			JTranscWindow.screenWidth = screenWidth;
			JTranscWindow.screenHeight = screenHeight;

			JTranscWindow.virtualWidth = virtualWidth;
			JTranscWindow.virtualHeight = virtualHeight;

			double virtualScale = Math.min(screenWidth / virtualWidth, screenHeight / virtualHeight);

			JTranscWindow.virtualActualWidth = screenWidth / virtualScale;
			JTranscWindow.virtualActualHeight = screenHeight / virtualScale;

			JTranscWindow.virtualScaleX = virtualScale;
			JTranscWindow.virtualScaleY = virtualScale;

			dispatchResized();

			return true;
		} else {
			return false;
		}
	}

	@JTranscKeep
	static public void referenced() {
	}
}
