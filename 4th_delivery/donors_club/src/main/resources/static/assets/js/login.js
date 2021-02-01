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

      sessionStorage.setItem("userId", user.id);
      sessionStorage.setItem("userName", user.name);
      location.replace("./index.html");
    } else {
      result.innerHTML = `email or password is empty`;
    }
  } catch (error) {
    result.innerHTML = `email or password is invalid`;
  }
});
