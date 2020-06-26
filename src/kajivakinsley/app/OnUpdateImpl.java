package kajivakinsley.app;

public interface OnUpdateImpl {

	void onDoneCleaning(String message);
	void onFoundDirt(String message);
	void onMoving(String messasge);
	void onDoneWithRoom(String message);
}
