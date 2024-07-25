import gearth.extensions.Extension;
import gearth.extensions.ExtensionInfo;
import gearth.protocol.packethandler.shockwave.packets.ShockPacketOutgoing;

import javax.swing.*;
import java.util.Objects;

@ExtensionInfo(
        Title = "AntiAfk",
        Description = "AntiAfk for Habbo Shockwave.",
        Version = "1.0",
        Author = "Thauan"
)

public class AntiAfk extends Extension {

    public AntiAfk(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        new AntiAfk(args).run();
    }

    public static AntiAfk RUNNING_INSTANCE;

    Timer antiAfk = new Timer(120000, e -> {
        sendToServer(new ShockPacketOutgoing("AK@M@M"));
    });

    @Override
    protected void onStartConnection() {
    }

    @Override
    protected void initExtension() {
        RUNNING_INSTANCE = this;

        onConnect((host, port, APIVersion, versionClient, client) -> {
            if (!Objects.equals(versionClient, "SHOCKWAVE")) {
                System.exit(0);
            }

            antiAfk.start();
        });

    }


    public static void waitAFckingSec(int millisecActually) {
        try {
            Thread.sleep(millisecActually);
        } catch (InterruptedException ignored) {
        }
    }

}
