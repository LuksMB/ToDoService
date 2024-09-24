package com.distribuidos.models;

import java.net.InetAddress;
import java.util.Objects;

public class RequestInfo {
    private Integer id;
    private InetAddress address;
    private Integer port;

    // Construtor para inicializar a tupla
    public RequestInfo(Integer id, InetAddress address, Integer port) {
        this.id = id;
        this.address = address;
        this.port = port;
    }

    public Integer getId() {
        return id;
    }

    public InetAddress getAddress() {
        return address;
    }

    public Integer getPort() {
        return port;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         RequestInfo that = (RequestInfo) o;
         return Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(port, that.port);
     }
 
     @Override
     public int hashCode() {
         return Objects.hash(id, address, port);
     }

}