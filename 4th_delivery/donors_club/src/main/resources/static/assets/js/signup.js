import { getElement, setStorageItem } from "./utils.js";

const name = getElement("#name");
const email = getElement("#email");
const password = getElement("#password");
const district = getElement("#district");
const city = getElement("#city");
const submitBtn = getElement("#submit");
const result = getElement(".result");

window.addEventListener("DOMContentLoaded", async () => {
  try {
    let districts = await $.ajax({
      url: `/api/districts`,
      method: "get",
      dataType: "json",
    });

    district.innerHTML = districts
      .map((dist) => {
        return `<option value="${dist.id}">${dist.name}</option>`;
      })
      .join("");

    let cities = await $.ajax({
      url: `/api/districts/${district.value}/cities`,
      method: "get",
      dataType: "json",
    });

    city.innerHTML = cities
      .map((ct) => {
        return `<option value="${ct.id}">${ct.name}</option>`;
      })
      .join("");
  } catch (error) {}
});

district.addEventListener("change", async () => {
  try {
    let cities = await $.ajax({
      url: `/api/districts/${district.value}/cities`,
      method: "get",
      dataType: "json",
    });

    city.innerHTML = cities
      .map((ct) => {
        return `<option value="${ct.id}">${ct.name}</option>`;
      })
      .join("");
  } catch (error) {}
});

submitBtn.addEventListener("click", async (e) => {
  e.preventDefault();

  try {
    let newUser = {
      name: name.value,
      email: email.value,
      password: password.value,
      city: {
        id: city.value,
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

      sessionStorage.setItem("userId", user.id);
      sessionStorage.setItem("userName", user.name);
      location.replace("./index.html");
    } else {
      console.log(name.value.trim().length);
      console.log(email.value.trim().length);
      console.log(password.value.trim().length);
      result.innerHTML = `Name, email and password must not be empty`;
    }
  } catch (error) {
    result.innerHTML = `Error creating new user`;
  }
});
