import React from 'react';
import PropTypes from "prop-types";
import StartAllServiceButton from './StartAllServiceButton.jsx';

class Actions extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        var actionsInnerStyle = {width:'100%',display:'inline-block',position:'relative',height:'inherit'};
        var startAllServiceStyle = {width:'fit-content',left:'5%',position:'absolute'};

        return(<div className='actions-container' style={this.props.style}>
            <div className='actions-inner-container' style={actionsInnerStyle}>
                <StartAllServiceButton clusterPath={this.props.clusterPath} style={startAllServiceStyle} />
            </div>
        </div>);

    }
}

Actions.propTypes = {clusterPath: PropTypes.string.isRequired};

export default Actions;