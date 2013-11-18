package se.lnu.http;

public interface HTTPServerObserver {

	void serverConstructed();

	void serverStarted();

	void serverStopped();

	void closedAccept();

	void startedClient();

	void waitForClient();

}
