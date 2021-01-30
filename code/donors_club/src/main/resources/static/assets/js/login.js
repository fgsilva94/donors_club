import { getElement, setStorageItem } from "./utils.js";

const email = getElement("#email");
const password = getElement("#password");
const submitBtn = getElement("#submit");
const result = getElement(".result");

submitBtn.addEventListener("click", async (e) => {
    e.preventDefault();

    let user;
    try {
        if (email.value && password.value) {
            user = await $.ajax({
                url: `/api/users/${email.value}/${password.value}/`,
                method: "get",
                dataType: "json",
            });
        }
    } catch (error) { }

    if (!user) {
        result.innerHTML = `email or password invalid`;
    } else {
        result.innerHTML = `Welcome ${user.name}`;
        setStorageItem("userId", user.id);
        setStorageItem("userName", user.name);
    }
});
