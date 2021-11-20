var itensRow = [];

function loadModalData (itemIndex, id) {
  itensRow = document.getElementsByClassName('line-w');

  let dateSplited = itensRow[itemIndex].children[2].innerText.split('/');
  let dateConvert = `${dateSplited[2]}-${dateSplited[1]}-${dateSplited[0]}`;

  console.log(itensRow[itemIndex].children[1]);

  document.getElementById('iptId').value = id;
  document.getElementById('iptPeso').value = itensRow[itemIndex].children[1].innerText.replace('Kg','');
  document.getElementById('iptData').value = dateConvert;

  openModal('#atualizarPeso');
}