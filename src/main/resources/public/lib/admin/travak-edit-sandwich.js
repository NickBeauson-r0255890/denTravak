import DenTravakAbstractElement from '../travak-abstract-element.js';
import '../common/travak-input-text.js';

class DenTravakEditSandwich extends DenTravakAbstractElement {

    constructor() {
        super('travak-admin-app')
    }

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
    }

    initEventListeners() {
        this.byId('save-btn').addEventListener('click', e => this.saveSandwich());
        this.byId('cancel-btn').addEventListener('click', e => this.app().showSandwichList());
    }

    saveSandwich() {
        //todo: call backend via fetch api and save sandwich
        let newSandwich = {};
        newSandwich.id = this.sandwich.id;
        newSandwich.name = this.sandwich.name;
        newSandwich.ingredients = this.sandwich.ingredients;
        newSandwich.price = this.sandwich.price;
        alert(JSON.stringify(newSandwich));
    
        fetch("http://localhost:8080/sandwiches",{
            method:"POST",
            headers:{
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newSandwich)

        })
        .then(res=>res.json())
        .then(res => alert( JSON.stringify(data)));
/*
        fetch('http://localhost:8080/sandwiches',{
            method: "POST",
            mode: "cors",
            cache: "no-cache",
            headers:{
                "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(sandwich),
        })
        .then(response => response.json())
        .then(resAsJson => alert('Sandwich succesfully changed'));*/
        alert("test");
        this.app().dispatchEvent(new CustomEvent('save-succeeded', {detail: this.sandwich}));
    }

    init(sandwich) {
        if(sandwich) {
            this.sandwich = sandwich;
            this.byId('title').innerHTML = `Bewerk broodje ${sandwich.name}`;
            this.byId('name').value = sandwich.name;
            this.byId('ingredients').value = sandwich.ingredients;
            this.byId('price').value = sandwich.price;
        } else {
            this.sandwich = {};
            this.byId('title').innerHTML = `Maak een nieuw broodje`;
            this.byId('name').value = "";
            this.byId('ingredients').value = "";
            this.byId('price').value = "";
        }
    }

    get template() {
        return `
            <h4 id="title">Bewerk een broodje</h4>
            <travak-input-text id="name" label="Sandwich naam" placeholder="De naam van het broodje"></travak-input-text>
            <travak-input-text id="ingredients" label="Ingredienten" placeholder="Welke ingredienten heeft het broodje"></travak-input-text>
            <travak-input-text id="price" label="Prijs" placeholder="De prijs van het broodje"></travak-input-text>
            <button id="cancel-btn" type="button" class="btn">Cancel</button>
            <button id="save-btn" type="button" class="btn btn-primary">Save</button>
        `;
    }

}

customElements.define('travak-edit-sandwich', DenTravakEditSandwich);