class AbstractDenTravakElement extends HTMLElement{
    connectedCallback(){
        this.initShadowDom();
    }

    initShadowDom(){
        let shadowRoot =
            shadowRoot.innerHTML = this.template;
    }

    get template(){
        throw "you did not define a template";
    }

    byId(id){
        return this.shadowRoot.querySelector('#${id}'); //string interpolation
    }
}