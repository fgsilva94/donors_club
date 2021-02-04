const getElement = (selection) => {
  const element = document.querySelector(selection);
  if (element) return element;
  throw new Error(
    `Please check "${selection}" selector, no such element exist`
  );
};

const getElements = (selection) => {
  const element = document.querySelectorAll(selection);
  if (element) return element;
  throw new Error(
    `Please check "${selection}" selector, no such element exist`
  );
};

const getStorageItem = (item) => {
  let storageItem = sessionStorage.getItem(item);

  if (storageItem) {
    storageItem = JSON.parse(sessionStorage.getItem(item));
  } else {
    storageItem = null;
  }
  return storageItem;
};

const setStorageItem = (name, item) => {
  sessionStorage.setItem(name, JSON.stringify(item));
};

const removeStorageItem = (name) => {
  sessionStorage.removeItem(name);
};

export {
  getElement,
  getElements,
  getStorageItem,
  setStorageItem,
  removeStorageItem,
};
