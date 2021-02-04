import { getElements, getStorageItem } from "./utils.js";

const login_toggle = getElements(".login-btn-toggle");
const logout_toggle = getElements(".logout-btn-toggle");

const toggleBtn = () => {
  if (getStorageItem("userId")) {
    for (const item of login_toggle) {
      item.classList.add("d-none");
    }
    for (const item of logout_toggle) {
      item.classList.remove("d-none");
    }
  } else {
    for (const item of login_toggle) {
      item.classList.remove("d-none");
    }
    for (const item of logout_toggle) {
      item.classList.add("d-none");
    }
  }
};

export { toggleBtn };
