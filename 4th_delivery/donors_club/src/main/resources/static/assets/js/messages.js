import { getElement, getStorageItem } from "./utils.js";

const messagesField = getElement("#messages");
const textMessage = getElement("#text-message");
const submitBtn = getElement("#submit");

window.addEventListener("DOMContentLoaded", async () => {
    buildMessages();
});

async function buildMessages() {
    try {
        let messages = await $.ajax({
            url: `/api/messages/${getStorageItem("chatId")}`,
            method: "get",
            dataType: "json",
        });
    console.log(messages)
        let userId = getStorageItem("userId");
        messagesField.innerHTML = messages.map(msg => {
            return `<li class="mb-3">
                        <div class="rounded p-3" data-id="${msg.id}" style="width: 60%; ${msg.sender.id === userId 
                                                        ? 'text-align: right; background-color:#ceddff; margin-left: auto;'
                                                        : 'text-align: left; background-color:#f2f4f5;'}">
                            <small class="sender" data-id="${msg.sender.id}">${msg.sender.name}</small>
                            <p>${msg.text}</p>
                            <small>${msg.time}</small>
                        </div>
                    </li>`
        }).join('');
    } catch (error) { }
}

submitBtn.addEventListener("click", async (e) => {
    e.preventDefault();

    try {
        let newMSG = {
            text: textMessage.value,
            time: new Date(),
            chat: {
                id: getStorageItem("chatId"),
            },
            sender: {
                id: getStorageItem("userId"),
            }
        };

        console.log(newMSG);

        if (textMessage.value.trim().length !== 0) {
            let msg = await $.ajax({
                url: `/api/messages`,
                method: "post",
                dataType: "json",
                data: JSON.stringify(newMSG),
                contentType: "application/json",
            });
            textMessage.value = "";
            buildMessages();
        } else {
            result.innerHTML = `Message must not be empty`;
        }
    } catch (error) {
        result.innerHTML = `Error creating new ad`;
    }
});