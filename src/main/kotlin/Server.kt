import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.SocketException
import java.util.*

private fun main() {
    try {
        val serverSocket = DatagramSocket(50001)

        val receivingDataBuffer = ByteArray(50001)
        var sendingDataBuffer = ByteArray(1024)

        val inputPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
        println("Waiting for a client to connect...")

        serverSocket.receive(inputPacket)

        val receivedData = String(inputPacket.data)
        println("Sent from the client: $receivedData")

        sendingDataBuffer = receivedData.uppercase(Locale.getDefault()).toByteArray()

        val senderAddress = inputPacket.address
        val senderPort = inputPacket.port

        val outputPacket = DatagramPacket(
                sendingDataBuffer, sendingDataBuffer.size,
                senderAddress, senderPort
        )
        serverSocket.send(outputPacket)
        serverSocket.close()
    } catch (e: SocketException) {
        e.printStackTrace()
    }
}