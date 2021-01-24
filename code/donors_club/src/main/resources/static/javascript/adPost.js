import { getElement, getStorageItem } from "./utils.js";

const _ad = getElement(".ad");

window.addEventListener("DOMContentLoaded", async () => {
  let ad;
  const id = getStorageItem("adId");
  try {
    ad = await $.ajax({
      url: `/api/ads/${id}`,
      method: "get",
      dataType: "json",
    });

    const { title, description } = ad;

    _ad.innerHTML = `
      <p>Title: ${title}</p>
      <p>Description: ${description}</p>`;
  } catch (error) {}
});
