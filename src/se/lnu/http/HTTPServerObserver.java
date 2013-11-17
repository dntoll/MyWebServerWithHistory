package se.lnu.http;

public interface HTTPServerObserver {

	void serverConstructed();

	void serverStarted();

	void serverStopped();

}
