import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketException

private fun main() {
    try {
        val clientSocket = DatagramSocket()
        val IPAddress = InetAddress.getByName("localhost")

        val output = ByteArrayOutputStream()
        val writer = DataOutputStream(output)

        val byte = 1400
        val string = "Hello"
        val long = -1L
        writer.writeByte(byte)
        writer.writeChars(string)
        writer.writeLong(long)

        val bytes = output.toByteArray()

        val receivingDataBuffer = ByteArray(1024)
        val sendingPacket =  DatagramPacket(bytes, bytes.size, IPAddress, 50001)

        clientSocket.send(sendingPacket)

        val receivingPacket = DatagramPacket(receivingDataBuffer,receivingDataBuffer.size)
        clientSocket.receive(receivingPacket)

        val receivedData = String(receivingPacket.data)
        println("Sent from the server: $receivedData")

        clientSocket.close()
    }
    catch(e: SocketException) {
        e.printStackTrace()
    }
}
