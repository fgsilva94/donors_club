import { getElement, getStorageItem, removeStorageItem } from "./utils.js";
import { toggleBtn } from "./loginBtnToggle.js";

const _ad = getElement(".ad");
const textMessage = getElement("#text-message");
const submitBtn = getElement("#submit1");
const logout = getElement(".logout");

window.addEventListener("DOMContentLoaded", async () => {
  toggleBtn();

  let ad;
  const id = getStorageItem("adId");
  try {
    ad = await $.ajax({
      url: `/api/ads/${id}`,
      method: "get",
      dataType: "json",
    });

    const { title, description } = ad;

    _ad.innerHTML = `
      <p>Title: ${title}</p>
      <p>Description: ${description}</p>`;
  } catch (error) {}
});

submitBtn.addEventListener("click", async (e) => {
  e.preventDefault();

  try {
    if (textMessage.value.trim().length !== 0) {
      let newChat = {
        ad: {
          id: getStorageItem("adId"),
        },
        user: {
          id: getStorageItem("userId"),
        },
        date: new Date(),
      };

      let chatId = await $.ajax({
        url: `/api/chats`,
        method: "post",
        dataType: "json",
        data: JSON.stringify(newChat),
        contentType: "application/json",
      });

      let newMSG = {
        text: textMessage.value,
        time: new Date(),
        chat: {
          id: chatId,
        },
        sender: {
          id: getStorageItem("userId"),
        },
      };

      let msg = await $.ajax({
        url: `/api/messages`,
        method: "post",
        dataType: "json",
        data: JSON.stringify(newMSG),
        contentType: "application/json",
      });

      sessionStorage.setItem("chatId", chatId);
      textMessage.value = "";
      window.location = "messages.html";
    } else {
      alert(`Message must not be empty`);
    }
  } catch (error) {
    if (!getStorageItem("userId")) {
      alert(`You must login first`);
    } else {
      alert(error.responseJSON.message);
    }
  }
});

logout.addEventListener("click", function (e) {
  e.preventDefault();

  removeStorageItem("adId");
  removeStorageItem("userId");
  removeStorageItem("userName");

  location.replace("./");
});
