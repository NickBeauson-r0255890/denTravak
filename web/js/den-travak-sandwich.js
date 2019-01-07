class DenTravakSandwich extends HTMLElement
{
    ConnectedCallback(){
        this.initShadowDom();
        this.fetchAndShowSandwiches();
    }

    initShadowDom(){
        let shadowRoot = this.attachShadow({});
        shadowRoot.innerHTML = this.template;
    }

    fetchAndShowSandwiches(){
        fetch('/api/sandwiches.json')  //Haalt code uit json
            .then(resp => resp.json())
            .then(sandwiches => this.showSandwiches(sandwiches));
    }

    showSandwiches(sandwiches){
        this.shadowRoot.querySelector('#sandwiches'); //id ophalen
        sandwichesUI.innerHTML = ''; //clear what's in tags
        sandwiches.forEach(sandwich =>{
            let sandwichLi = document.createElement('li');
            sandwichesUI.appendChild('sandwichLi');
            sandwichLi.innerHTML = sandwich.name;
        });
    }

    get template(){
        return '<ul id="sandwiches"><li>Tekst</li></ul>';
    }
}

customElements.define('dt-sandwich-list', DenTravakSandwich); // link tag to class