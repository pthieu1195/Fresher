package com.Client;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Clients")
@XmlAccessorType(XmlAccessType.FIELD)
public class Clients {
	@XmlElement(name = "Client")
	private List<Client> list = null;

	public List<Client> getClients() {
		return list;
	}

	public void setClients(List<Client> list) {
		this.list = list;
	}

	public Clients() {

	}
}