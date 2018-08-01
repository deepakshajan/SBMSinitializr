import React from 'react';
import PropTypes from 'prop-types';
import AtomicProcessPanel from "./AtomicProcessPanel.jsx";

class MainSection extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        let atomicProcessPanelStyle = {width:'99%'};

        return (<div className='main-section-container' style={this.props.style}>
            <div className='main-section-inner-container'>
                <AtomicProcessPanel title='Root' showLogButton={true} style={atomicProcessPanelStyle}/>
            </div>
        </div>);
    }


}

MainSection.propTypes = {processData: PropTypes.object.isRequired};

export default MainSection;