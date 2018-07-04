import React from 'react';

class MainSection extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        return (<div className='main-section-container' style={this.props.style}>
            <div className='main-section-inner-container'>

            </div>
        </div>);
    }

}

export default MainSection;