package ru.xlv.packetapi.example.shop;

import io.netty.buffer.ByteBufOutputStream;
import ru.xlv.packetapi.client.packet.IPacketCallbackEffective;
import ru.xlv.packetapi.common.util.ByteBufInputStream;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public class PacketShopCategoryGet implements IPacketCallbackEffective<PacketShopCategoryGet.Result> {

    private final Result result = new Result();

    private String category;

    public PacketShopCategoryGet() {}

    public PacketShopCategoryGet(String category) {
        this.category = category;
    }

    @Override
    public void writeCallback(ByteBufOutputStream bbos) throws IOException {
        bbos.writeUTF(category);
    }

    @Override
    public void readCallback(ByteBufInputStream bbis) throws IOException {
        result.shopItemList = readObjects(bbis, ShopItem.class);
        result.responseMessage = bbis.readUTF();
    }

    @Nullable
    @Override
    public Result getResult() {
        return result;
    }

    public static class Result {
        private String responseMessage;
        private List<ShopItem> shopItemList;

        public String getResponseMessage() {
            return responseMessage;
        }

        public List<ShopItem> getShopItemList() {
            return shopItemList;
        }
    }
}