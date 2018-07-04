import React from 'react';

class Actions extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        var actionsInnerStyle = {width:'100%',display:'inline-block',position:'relative',height:'inherit'};

        return(<div className='actions-container' style={this.props.style}>
            <div className='actions-inner-container' style={actionsInnerStyle}></div>
            {/*All the action components go here*/}
        </div>);
    }
}

export default Actions;