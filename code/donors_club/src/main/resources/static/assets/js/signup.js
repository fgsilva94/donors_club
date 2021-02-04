import { getElement, setStorageItem, removeStorageItem } from "./utils.js";
import { toggleBtn } from "./loginBtnToggle.js";

const name = getElement("#name");
const email = getElement("#email");
const password = getElement("#password");
const district = getElement("#dropdown-dist");
const btnDropdown = getElement("#btn-district");
const submitBtn = getElement("#submit");
const paramDist = getElement("#param-dist");
const logout = getElement(".logout");

window.addEventListener("DOMContentLoaded", async () => {
  toggleBtn();

  try {
    let districts = await $.ajax({
      url: `/api/districts`,
      method: "get",
      dataType: "json",
    });

    district.innerHTML = districts
      .map((dist) => {
        let cities = dist.cities
          .map((city) => {
            return `<li><a class="dropdown-item" data-id="${city.id}">${city.name}</a></li>`;
          })
          .join("");

        return `<li>
                  <a class="dropdown-item dropdown-toggle">${dist.name}</a>
                  <ul class="submenu dropdown-menu">
                      ${cities}
                  </ul>
                        </li>`;
      })
      .join("");

    $("#dropdown-dist .dropdown-item").on("click", (e) => {
      btnDropdown.setAttribute("aria-expanded", "false");
      btnDropdown.innerText = e.target.textContent;
      paramDist.value = e.target.getAttribute("data-id");
    });
  } catch (error) {
    console.log(error);
  }
});

submitBtn.addEventListener("click", async (e) => {
  e.preventDefault();

  try {
    let newUser = {
      name: name.value,
      email: email.value,
      password: password.value,
      city: {
        id: paramDist.value,
      },
    };

    if (
      name.value.trim().length !== 0 &&
      email.value.trim().length !== 0 &&
      password.value.trim().length !== 0
    ) {
      let user = await $.ajax({
        url: `/api/users`,
        method: "post",
        dataType: "json",
        data: JSON.stringify(newUser),
        contentType: "application/json",
      });

      location.replace("./login.html");
    } else {
      alert(`Name, email and password must not be empty`);
    }
  } catch (error) {
    alert(`Error creating new user!`);
  }
});

logout.addEventListener("click", function (e) {
  e.preventDefault();

  removeStorageItem("adId");
  removeStorageItem("userId");
  removeStorageItem("userName");

  location.replace("./");
});
