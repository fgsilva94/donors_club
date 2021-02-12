import { getElement, getStorageItem, removeStorageItem } from "./utils.js";
import { toggleBtn } from "./loginBtnToggle.js";

const chatsField = getElement("#chats");
const logout = getElement(".logout");

window.addEventListener("DOMContentLoaded", async () => {
  toggleBtn();

  try {
    let chats = await $.ajax({
      url: `/api/users/${getStorageItem("userId")}/chats`,
      method: "get",
      dataType: "json",
    });

    chatsField.innerHTML = chats
      .map((chat) => {
        return `<tr class="chat-field" data-id="${chat.id}">
                        <td>${chat.user}</td>
                        <td>${chat.adTitle}</td>
                        <td>${chat.updated}</td>
                    </tr>`;
      })
      .join("");

    $("#chats tr").on("click", (e) => {
      let chatId = e.currentTarget.getAttribute("data-id");
      sessionStorage.setItem("chatId", chatId);
      window.location = "messages.html";
    });
  } catch (error) {}
});

logout.addEventListener("click", function (e) {
  e.preventDefault();

  removeStorageItem("adId");
  removeStorageItem("userId");
  removeStorageItem("userName");

  location.replace("./");
});
