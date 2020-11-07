import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

class JVMTIThread {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().contains("Main")) {
                System.out.println(vmd.displayName());
                System.out.println(vmd.id());
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("core-1.0-SNAPSHOT-jar-with-dependencies.jar", "args1");
                System.out.println("ok");
                virtualMachine.detach();
            }
        }
    }
}