import { getElement, getStorageItem } from "./utils.js";

const chatsField = getElement("#chats");

window.addEventListener("DOMContentLoaded", async () => {
    try {
        let chats = await $.ajax({
            url: `/api/chats/user/${getStorageItem("userId")}`,
            method: "get",
            dataType: "json",
        });

        chatsField.innerHTML = chats.map(chat => {
            return `<tr class="chat-field" data-id="${chat.id}">
                        <td>${chat.user}</td>
                        <td>${chat.adTitle}</td>
                        <td>${chat.updated}</td>
                    </tr>`
        }).join('');

        $("#chats tr").on('click', e => {
            let chatId = e.currentTarget.getAttribute('data-id');
            console.log(chatId)
            sessionStorage.setItem("chatId", chatId);
            window.location = "messages.html";
        })
    } catch (error) { }
});
