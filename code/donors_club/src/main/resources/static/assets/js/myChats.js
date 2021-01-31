import { getElement, getStorageItem } from "./utils.js";

const chatsField = getElement(".chats");

window.addEventListener("DOMContentLoaded", async () => {
  try {
    let chats = await $.ajax({
      url: `/api/chats/${getStorageItem("userId")}`,
      method: "get",
      dataType: "json",
    });

    console.log(chats);
  } catch (error) {}
});
