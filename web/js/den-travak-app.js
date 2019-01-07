//bendyworks site native web components
//bendyworks.com/blog/native-web-components
class DenTravakApp extends HTMLElement{
    ConnectedCallback(){
        this.innerHTML = this.template;
    }

    get template(){
        return '<div><dt-sandwich-list></dt-sandwich-list></div>';
    }
}

customElements.define('dt-app',DenTravakApp);