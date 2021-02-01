import { getElement, getStorageItem } from "./utils.js";

const _user = getElement(".user");

window.addEventListener("DOMContentLoaded", async () => {
  try {
    let user = await $.ajax({
      url: `/api/users/${getStorageItem("userId")}`,
      method: "get",
      dataType: "json",
    });

    const {
      name,
      email,
      city: {
        name: cityName,
        district: { name: districtName },
      },
    } = user;

    _user.innerHTML = `
      <p>Name: ${name}</p>
      <p>Email: ${email}</p>
      <p>Address: ${cityName}, ${districtName}</p>`;
  } catch (error) {}
});
