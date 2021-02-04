import { getElement, setStorageItem, removeStorageItem } from "./utils.js";

const email = getElement("#email");
const password = getElement("#password");
const submitBtn = getElement("#submit");
const logout = getElement(".logout");

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
      location.replace("./");
    } else {
      alert(`email or password is empty`);
    }
  } catch (error) {
    alert(`email or password is invalid`);
  }
});

logout.addEventListener("click", function (e) {
  e.preventDefault();

  removeStorageItem("adId");
  removeStorageItem("userId");
  removeStorageItem("userName");

  location.replace("./");
});
