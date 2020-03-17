// IIPCService.aidl
package com.example.ipc;

// Declare any non-default types here with import statements
import com.example.ipc.model.Request;
import com.example.ipc.model.Respson;
interface IIPCService {

    Respson send(in Request request);
}
