import { getElement, getStorageItem } from "./utils.js";

const messagesField = getElement("#messages");

window.addEventListener("DOMContentLoaded", async () => {
    try {
        let messages = await $.ajax({
            url: `/api/messages/${getStorageItem("chatId")}`,
            method: "get",
            dataType: "json",
        });

        messagesField.innerHTML = messages.map(msg => {
            return `<li>
                        <div>
                            
                        </div>
                    </li>`
        }).join('')

        console.log(messages)
    } catch (error) { }
});