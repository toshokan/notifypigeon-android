# notifypigeon-android

NotifyPigeon-android is a mobile application that sends notification information to a listener over a TCP/IP socket. It currently supports alerting the listener when notifications are 
created, and also letting it know when they are dismissed.

It is still very primitive and sends data over the network in plain text. It also still requires that the destination IP address and TCP port be known at compile time.
