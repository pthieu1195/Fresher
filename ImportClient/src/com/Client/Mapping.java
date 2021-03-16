package com.Client;

public class Mapping {
public Mapping(String clientColumn, String xmlTagName) {
		super();
		this.clientColumn = clientColumn;
		this.xmlTagName = xmlTagName;
	}

private String clientColumn;
private String xmlTagName;


public String getClientColumn() {
	return clientColumn;
}
public void setClientColumn(String clientColumn) {
	this.clientColumn = clientColumn;
}
public String getXmlTagName() {
	return xmlTagName;
}
public void setXmlTagName(String xmlTagName) {
	this.xmlTagName = xmlTagName;
}



}
