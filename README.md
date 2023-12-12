# Chatty Chat Chat Project
## Advanced Programming in Java - COSC 150

This project, "Chatty Chat Chat," aims to implement a real-time chat protocol using Java sockets and threads. The protocol, termed the ChattyChatChat protocol (CCC), enables seamless communication between a server and multiple clients.

### Table of Contents
- [Project Overview](#project-overview)
- [Technical Specification](#technical-specification)
- [Getting Started](#getting-started)
- [Features](#features)
  
## Project Overview

The Chatty Chat Chat project is designed to provide a foundational understanding of network communications in Java. It demonstrates the use of Java sockets for creating and sustaining network connections and employs Java threads for concurrent execution, allowing multiple client interactions simultaneously.

## Technical Specification

### Core Components
- **ChattyChatChatServer**: A server program that listens for connections from clients on a specified port.
- **ChattyChatChatClient**: A client program that connects to the server, allowing users to send and receive messages.

### Networking Model
- The project models network connections using sequence diagrams to describe complete network interactions and the communications between client-server processes and their supporting threads.

## Getting Started

To get started with the Chatty Chat Chat project, clone the repository and follow the setup instructions provided in the [installation guide](link-to-installation-guide).

## Features

- **Real-Time Messaging**: Allows real-time text messaging between clients through a central server.
- **Multithreading**: Utilizes Java threads to handle multiple client connections and interactions concurrently.
- **Custom Protocol Commands**: Supports commands like `/nick` for setting nicknames, `/dm` for direct messages, and `/quit` for disconnecting.
